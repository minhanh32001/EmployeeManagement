function validateForm() {
    let employee_name = document.forms["employee_data"]["employee_name"].value;
    let email = document.forms["employee_data"]["email"].value;
    let phone_number = document.forms["employee_data"]["phone_number"].value;
    let emailRegex = /^[A-Za-z][\w$.]+@[A-Za-z]+\.[A-Za-z]+$/;
    if (employee_name.length >25) {
        alert("お名前は２５文字以内で入力してください。");
        return false;
    }
    else if(!emailRegex.test(email) || email.length >25){
        alert("メールアドレスは、25文字以内で正しく入力して下さい。");
        return false;
        }
    else if(phone_number.length>10) {
        alert("電話番号は、数字10文字以内で入力して下さい。");
        return false;
    }
    else return true;
}