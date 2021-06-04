(function () {
    'use strict'
    const forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                event.preventDefault()
                if (!form.checkValidity()) {
                    event.stopPropagation()
                } else {
                    sendRequest(form.name)
                }
                form.classList.add('was-validated')
            }, false)
        })
})()

function sendRequest(name) {
    let errors = document.getElementById("errors");
    errors.innerText = "";
    let form = document.forms[name]
    jQuery.ajax({
        url: form.action,
        data: $('form').serialize(),
        cache: false,
        contentType: false,
        processData: false,
        method: 'GET',
        success: calculate,
        error: parseError
    });
}

function calculate(data){
    let result = document.getElementById("value");
    result.value = data.result;
}

function parseError(data){
    let errors = document.getElementById("errors");
    errors.innerText = data.result;
}