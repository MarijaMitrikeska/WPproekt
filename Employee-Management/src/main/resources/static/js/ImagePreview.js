// var reader = new FileReader();
// reader.onload = function(r_event) {
//     document.getElementById('prev').setAttribute('src', r_event.target.result);
// }
//
// document.getElementsByName('file')[0].addEventListener('change', function(event) {
//     reader.readAsDataURL(this.files[0]);
// });



function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('#blah').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}

$("#imgInp").change(function() {
    readURL(this);
});