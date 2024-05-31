$(document).ready(function() {
    import "tablestyle.css";
    import "add.css";
    $('#openModalButton').on('click', function() {
        $('#modal').show();
    });

    $('#addButton').on('click', function() {
        var name = $('#nameInput').val();

        if (name.trim() === '') {
            alert('Пожалуйста, введите имя');
            return;
        }
        $.ajax({
            url: '/add-data',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ name: name }),
        }).done(function() {
            setTimeout(function(){
                alert('Данные успешно добавлены');
            }, 0);
            setTimeout(function(){
                document.querySelector('.alert').style.display = 'none';
            }, 2000);
            $('#modal').hide();
            location.reload();
        }).fail(function() {
            alert('Ошибка добавления. Попробуйте еще раз.');
        });
    });
});