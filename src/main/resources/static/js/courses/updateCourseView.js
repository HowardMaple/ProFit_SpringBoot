$(document).ready(function () {
    // 獲取URL中的參數
    let params = new URLSearchParams(window.location.search);
    let oldCourseId = params.get('courseId');

    // 如果 courseId 存在且不為空字串，則發送 AJAX 請求獲取課程信息
    if (oldCourseId) {

        $.ajax({
            url: contextPath + '/courses/search/' + oldCourseId, // 使用 contextPath 和路徑變數
            dataType: 'json',
            type: 'GET',
            success: function (response) {

                // 獲取課程數據
                let course = response.course;

                // 獲取所有課程類別
                let majorCategories = response.majorCategories;

                // 日期格式處理
                let courseStartDate = `${course.courseStartDate.split('.')[0]}`;
                let courseEndDate = `${course.courseEndDate.split('.')[0]}`;
                let courseStartDateTime = `${courseStartDate}`;
                let courseEndDateTime = `${courseEndDate}`;

                // 動態生成課程類別的選項
                let majorCategoryOptions = '<option value="">請選擇類別</option>';
                majorCategories.forEach(function (category) {
                    let selected = course.courseCategoryId === category.majorCategoryId ? 'selected' : '';
                    majorCategoryOptions += `<option value="${category.majorCategoryId}" ${selected}>${category.categoryName}</option>`;
                });


                $('.form-container').append(`
                    <form>
                        <div class="form-group">
                            <label for="courseName">課程名稱:</label>
                            <input type="text" id="courseName" name="courseName" value="${course.courseName}">
                        </div>
						<div class="form-group">
						    <label for="courseMajor">課程類別:</label>
						    <select id="courseMajor" name="courseMajor" required>
						        ${majorCategoryOptions}
						    </select>
						</div>
                        <div class="form-group">
                            <label for="courseCreateUserId">課程創建者ID:</label>
                            <input type="text" id="courseCreateUserId" name="courseCreateUserId" value="${course.courseCreaterId}" >
                        </div>
                        <div class="form-group">
                            <label for="courseInformation">課程資訊:</label>
                            <textarea id="courseInformation" name="courseInformation" rows="4" cols="50" >${course.courseInformation}</textarea>
                        </div>
						<div class="form-group">
						    <label for="courseCoverPicture">課程封面圖片: (建議比例16:9、小於1920 x 1080像素)</label>

						    <!-- 顯示原本的圖片 -->
						    <div style="text-align: center">
						        <label>目前圖片:</label>
						        <img id="currentCoverImage" src="${course.courseCoverPictureURL}" alt="Course Cover Picture" style="max-width: 300px; height: auto;">
						    </div>

						    <!-- 允許用戶上傳新圖片 -->
						    <input type="file" id="courseCoverPicture" name="courseCoverPicture">

						    <!-- 隱藏字段，保存原始圖片 URL，如果未選擇新圖片，則保留此圖片 -->
						    <input type="hidden" name="originalCourseCoverPictureURL" value="${course.courseCoverPictureURL}">
						</div>
						<div class="form-group">
                            <label for="courseDescription">課程描述:</label>
                            <textarea id="courseDescription" name="courseDescription" rows="6" cols="50" >${course.courseDescription}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="courseEnrollmentDate">修改日期: (自動帶入)</label>
                            <input type="date" id="courseEnrollmentDate" name="courseEnrollmentDate" readonly>
                        </div>
                        <div class="form-group">
                            <label for="courseStartDate">開始日期:</label>
                            <input type="datetime-local" id="courseStartDate" name="courseStartDate" value="${courseStartDateTime}">
                        </div>
                        <div class="form-group">
                            <label for="courseEndDate">結束日期:</label>
                            <input type="datetime-local" id="courseEndDate" name="courseEndDate" value="${courseEndDateTime}">
                        </div>
                        <div class="form-group">
                            <label for="coursePrice">課程價格:</label>
                            <input type="number" id="coursePrice" name="coursePrice" value="${course.coursePrice}" >
                        </div>
                        <div class="form-group">
                            <label for="courseStatus">課程狀態:</label>
                            <select id="courseStatus" name="courseStatus" required>
                                <option value="">請選擇狀態</option>
                                <option value="Active">啟用</option>
                                <option value="Pending">審核中</option>
                                <option value="Closed">已關閉</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <a href="${contextPath}/b/courses"><button id='cancelBtn' type="button" style="margin-right:330px;">取消修改</button></a>
                            <button id="editBtn" name="editBtn" type="submit" >修改課程</button>
                        </div>
                    </form>
                `);

                // 設置修改日期為當前日期
                let enrollmentDateInput = document.getElementById("courseEnrollmentDate");
                if (enrollmentDateInput) {
                    let now = new Date();
                    let year = now.getFullYear();
                    let month = String(now.getMonth() + 1).padStart(2, '0'); // 月份從0開始，所以要+1
                    let day = String(now.getDate()).padStart(2, '0');
                    let formattedDate = `${year}-${month}-${day}`;
                    enrollmentDateInput.value = formattedDate;

                } else {
                    console.error('enrollmentDateInput element not found');
                }

                // 設置課程類別和狀態
                $('#courseMajor').val(course.courseCategoryId);
                $('#courseStatus').val(course.courseStatus);
            },
            error: function (error) {
                console.error('Error fetching course for editing:', error);
            }
        });
    }
});

// 日期轉換函數
function convertToSQLDateTimeFormat(datetimeLocal) {
    // 將 "T" 替換成 " "，將 "YYYY-MM-DDTHH:MM" 轉換為 "YYYY-MM-DD HH:MM"
    return datetimeLocal.replace("T", " ") + ":00"; // 加上秒部分，確保格式 "YYYY-MM-DD HH:MM:SS"
};

// 執行修改課程
$(document).on('click', '#editBtn', function (event) {
    event.preventDefault(); // 防止表單默認提交行為

    // 獲取 URL 中的 courseId 參數
    let params = new URLSearchParams(window.location.search);
    let oldCourseId = params.get('courseId');

    if (!oldCourseId) {
        alert('無法獲取課程 ID');
        return;
    }

    // 獲取表單元素的值
    let courseStartDate = $('#courseStartDate').val();
    let courseEndDate = $('#courseEndDate').val();
    courseStartDate = convertToSQLDateTimeFormat(courseStartDate);
    courseEndDate = convertToSQLDateTimeFormat(courseEndDate);

    // 使用 FormData 來封裝表單數據，包括文件
    let formData = new FormData();
    formData.append('courseName', $('#courseName').val());
    formData.append('courseCategory', $('#courseMajor').val());
    formData.append('courseCreateUserId', $('#courseCreateUserId').val());
    formData.append('courseInformation', $('#courseInformation').val());
    formData.append('courseDescription', $('#courseDescription').val());
    formData.append('courseEnrollmentDate', $('#courseEnrollmentDate').val());
    formData.append('courseStartDate', courseStartDate);
    formData.append('courseEndDate', courseEndDate);
    formData.append('coursePrice', $('#coursePrice').val());
    formData.append('courseStatus', $('#courseStatus').val());

    // 處理封面圖片檔案
    let courseCoverPictureFile = $('#courseCoverPicture')[0].files[0];
    if (courseCoverPictureFile) {
        formData.append('courseCoverPicture', courseCoverPictureFile);
    } else {
        // 如果未選擇新圖片，保留原始圖片
        formData.append('originalCourseCoverPictureURL', $('input[name="originalCourseCoverPictureURL"]').val());
    }

    $.ajax({
        url: contextPath + '/courses/update/' + oldCourseId, // 使用 contextPath 變數和路徑變數
        data: formData,
        processData: false,  // 告訴 jQuery 不要處理數據
        contentType: false,  // 告訴 jQuery 不要設置 contentType
        type: 'POST', // 使用 POST 方法
        success: function (response) {
            if (response) {
                window.alert('課程修改成功');
                window.location.href = contextPath + '/b/courses?clickButton=true';
            } else {
                window.alert('課程修改失敗');
            }
        },
        error: function (xhr, status, error) {
            console.error('發生錯誤:', error);
            alert('課程修改失敗，請重試。');
        }
    });
});