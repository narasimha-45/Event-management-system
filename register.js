function validateForm() {
    var name = document.forms["registerForm"]["name"].value;
    var gender = document.forms["registerForm"]["gender"].value;
    var email = document.forms["registerForm"]["email"].value;
    var phone = document.forms["registerForm"]["phone"].value;
    var password = document.forms["registerForm"]["password"].value;
    var confirmPassword = document.forms["registerForm"]["confirm_password"].value;
    if(password.length<6){
        alert("Password must be at least 6 characters long.");
        return false;
    }
    if (password !== confirmPassword) {
        alert("Passwords do not match");
        return false;
    }

    if (phone.length < 10 || isNaN(phone)) {
        alert("Please enter a valid phone number");
        return false;
    }

    return true;
}