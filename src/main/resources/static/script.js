// =======================================================
// === 1. (เพิ่ม) "ยาม FRONTEND" (ต้องอยู่บนสุด นอกสุด) ===
// =======================================================
const authToken = localStorage.getItem('authToken'); 

if (!authToken) {
    alert('คุณยังไม่ได้เข้าสู่ระบบ กรุณา Login ก่อนครับ');
    window.location.href = 'index.html'; // เตะกลับไปหน้า Login (index.html)
    throw new Error('Authentication required. Redirecting to login.');
}
// =======================================================


// =======================================================
// === 2. (เพิ่ม) Helper Functions (สำหรับ Security) ===
// =======================================================
function getAuthToken() {
    return localStorage.getItem('authToken');
}
function getAuthHeaders() {
    const token = getAuthToken();
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json' 
    };
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }
    return headers;
}
// =======================================================

// === รอให้หน้าเว็บโหลดเสร็จก่อนเริ่มทำงาน ===
document.addEventListener('DOMContentLoaded', () => {

    const API_URL = '/api'; // ตรวจสอบ URL ของ API ให้ถูกต้อง

    // ==========================================================
    // === ส่วนที่ 3: ทำงานได้ "ทุกหน้า" (Universal Code)
    // ==========================================================

    // --- 1. ทำให้ปุ่มย้อนกลับ (.btn-back) ทำงาน ---
    const backButton = document.querySelector('.btn-back');
    if (backButton) {
        backButton.addEventListener('click', () => {
            window.location.href = 'main.html'; // สั่งให้ย้อนกลับไปหน้าหลัก (main.html)
        });
    }

    // --- 2. ทำให้รหัสวิชาทุกอัน (.subject) คลิกแล้วไปหน้ารายละเอียดได้ ---
    document.querySelectorAll('.subject').forEach(span => {
        span.addEventListener('click', () => {
            // ดึงรหัสวิชาจาก id ก่อน, ถ้าไม่มีให้ดึงจากข้อความใน tag
            let courseId = span.id;
            if (!courseId) {
                // ทำความสะอาดข้อความ เช่น "คพ.100" -> "CS100"
                courseId = span.textContent.trim().replace('คพ.', 'CS').replace('มธ.', 'TU').replace('ส.', 'ST').replace('ค.', 'MA').replace('ศศ.', 'SS').replace('สษ.', 'EL');
            }
            
            if (courseId) {
                window.location.href = `courses-detail.html?course=${courseId}`;
            }
        });
    });

    // ==========================================================
    // === ส่วนที่ 4 ทำงานเฉพาะในหน้า index.html
    // ==========================================================
    const searchInput = document.getElementById('search-input');
    if (searchInput) {
        const searchResultsDiv = document.getElementById('search-results');

        const searchCourses = async (query) => {
            if (!query || query.trim().length < 2) {
                searchResultsDiv.innerHTML = '';
                searchResultsDiv.style.display = 'none';
                return;
            }
            try {
                const response = await fetch(`${API_URL}/courses?query=${encodeURIComponent(query)}`, { headers: getAuthHeaders() });
                if (response.status === 401) {
                    alert('Session หมดอายุ กรุณา Login ใหม่');
                    localStorage.removeItem('authToken');
                    window.location.href = 'index.html';
                    return;
                }
                const courses = await response.json();
                
                searchResultsDiv.innerHTML = '';
                searchResultsDiv.style.display = 'block';

                if (courses.length === 0) {
                    searchResultsDiv.innerHTML = '<div class="no-results">ไม่พบรายวิชา</div>';
                    return;
                }
				courses.forEach(course => {
				    const courseEl = document.createElement('div');
				    courseEl.className = 'result-item'; // <-- แก้ไข 1

				    // แก้ไข 2: ใช้ innerHTML เพื่อสร้างโครงสร้างที่ถูกต้อง
				    courseEl.innerHTML = `
				    <span class="result-item__code">${course.courseCode}</span>
				    <span class="result-item__name">${course.courseName}</span>
				                    `;
				                    
				    courseEl.addEventListener('click', () => {
				       window.location.href = `courses-detail.html?course=${course.courseCode}`;
				                    });
				    searchResultsDiv.appendChild(courseEl);
				                });
            } catch (error) {
                searchResultsDiv.innerHTML = '<div class="search-item error">เกิดข้อผิดพลาดในการค้นหา</div>';
            }
        };

        const curriculumBtn = document.querySelector('.title-card .btn');
        if (curriculumBtn) {
            curriculumBtn.addEventListener('click', () => {
                window.location.href = 'curriculum.html';
            });
        }
        
        let debounceTimer;
        searchInput.addEventListener('input', () => {
            clearTimeout(debounceTimer);
            debounceTimer = setTimeout(() => {
                searchCourses(searchInput.value);
            }, 400);
        });
    }

    // ==========================================================
    // === ส่วนที่ 5: ทำงานเฉพาะในหน้า courses-detail.html
    // ==========================================================
    const courseHeader = document.getElementById('course-header');
    if (courseHeader) {
        const courseCredit = document.getElementById('course-credit');
        const courseDescription = document.getElementById('course-description');
        const prerequisiteCoursesDiv = document.getElementById('prerequisite-courses');
        const nextCoursesDiv = document.getElementById('next-courses');

        const fetchCourseDetails = async (id) => {
            try {
                // (โค้ดที่ถูกต้อง)
                const response = await fetch(`${API_URL}/courses?query=${encodeURIComponent(query)}`, { headers: getAuthHeaders() });
                if (response.status === 401) {
                    alert('Session หมดอายุ กรุณา Login ใหม่');
                    localStorage.removeItem('authToken');
                    window.location.href = 'index.html';
                    return;
                }
                if (!response.ok) throw new Error('ไม่สามารถโหลดข้อมูลวิชาได้');
                const course = await response.json();

                document.title = `${course.courseCode} | CSTU Courses Explorer`; // อัปเดต Title ของหน้าเว็บ
                courseHeader.textContent = `${course.courseCode} — ${course.courseName}`;
                courseCredit.innerHTML = `${course.courseGroup || 'วิชา'} จำนวน <b>${course.credit} หน่วยกิต</b>`;
                courseDescription.textContent = course.courseDetail || 'ไม่มีคำอธิบาย';

                prerequisiteCoursesDiv.innerHTML = '';
                if (course.prerequisites && course.prerequisites.length > 0) {
                    course.prerequisites.forEach(preReqId => {
                        const link = document.createElement('a');
                        link.href = `courses-detail.html?course=${preReqId}`;
                        link.className = 'subject';
                        link.textContent = preReqId;
                        prerequisiteCoursesDiv.appendChild(link);
                        prerequisiteCoursesDiv.append(' ');
                    });
                } else {
                    prerequisiteCoursesDiv.textContent = 'ไม่มี';
                }

                nextCoursesDiv.innerHTML = '';
                if (course.nextCourses && course.nextCourses.length > 0) {
                     course.nextCourses.forEach(nextId => {
                        const link = document.createElement('a');
                        link.href = `courses-detail.html?course=${nextId}`;
                        link.className = 'subject';
                        link.textContent = nextId;
                        nextCoursesDiv.appendChild(link);
                        nextCoursesDiv.append(' ');
                    });
                } else {
                    nextCoursesDiv.textContent = 'ไม่มี';
                }

            } catch (error) {
                courseHeader.textContent = `เกิดข้อผิดพลาด`;
                courseDescription.textContent = `ไม่สามารถโหลดข้อมูลสำหรับวิชา ${id} ได้`;
            }
        };

        const params = new URLSearchParams(window.location.search);
        const courseId = params.get('course');

        if (courseId) {
            fetchCourseDetails(courseId);
        } else {
            courseHeader.textContent = 'ไม่พบรายวิชา';
            courseDescription.textContent = 'กรุณาเลือกรายวิชาจากหน้าหลัก';
        }
    }
});


//logout js//
document.addEventListener('DOMContentLoaded', () => {

    const btnLogout = document.getElementById('btnLogout');

    if (btnLogout) {
        btnLogout.addEventListener('click', () => {

            // ลบ token ที่เก็บไว้
            localStorage.removeItem('authToken');

            // redirect กลับไปหน้า login
            window.location.href = 'index.html';
        });
    }

});