document.addEventListener('DOMContentLoaded', () => {
    
    const loginForm = document.getElementById('login-form');
    const loginErrorDiv = document.getElementById('login-error');

    if (loginForm) {
        loginForm.addEventListener('submit', async (e) => {
            e.preventDefault(); // หยุดการ submit ฟอร์มแบบดั้งเดิม

            // 1. ซ่อนข้อความ Error เก่า (ถ้ามี)
            loginErrorDiv.style.display = 'none';
            loginErrorDiv.textContent = '';

            // 2. ดึงข้อมูลจากฟอร์ม
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            // 3. สร้างข้อมูลที่จะส่งไป Backend
            const loginData = {
                username: username,
                password: password
            };

            // 4. ส่งข้อมูลไปที่ Backend (นี่คือส่วน "เก็บข้อมูล")
            try {
                // 
                // *** หมายเหตุสำคัญ ***
                // คุณต้องไปสร้าง API Endpoint ที่ Backend (ใน Spring Boot)
                // เพื่อรับ POST request ที่ URL นี้ (เช่น /api/auth/login)
                // 
                const response = await fetch('http://localhost:8081/api/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(loginData)
                });

                if (response.ok) {
                    // 5.1 ถ้าล็อกอินสำเร็จ
                    const result = await response.json(); 
                    
                    // ตัวอย่าง: ถ้า Backend ส่ง token กลับมา
                    // localStorage.setItem('authToken', result.token); 
                    
                    // แจ้งเตือนว่าสำเร็จ (หรือจะเปลี่ยนหน้าเลยก็ได้)
                    alert('เข้าสู่ระบบสำเร็จ!');
                    
                    // ไปยังหน้า index.html
                    window.location.href = 'main.html';

                } else {
                    // 5.2 ถ้า Backend ตอบกลับมาว่า Error (เช่น รหัสผ่านผิด)
                    const errorData = await response.json();
                    showError(errorData.message || 'ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง');
                }

            } catch (error) {
                // 5.3 ถ้าเกิดข้อผิดพลาดในการเชื่อมต่อ (เช่น Backend ไม่ได้รัน)
                console.error('Login Error:', error);
                showError('ไม่สามารถเชื่อมต่อกับเซิร์ฟเวอร์ได้');
            }
        });
    }

    // ฟังก์ชันสำหรับแสดงข้อความ Error
    function showError(message) {
        loginErrorDiv.textContent = message;
        loginErrorDiv.style.display = 'block';
    }

});