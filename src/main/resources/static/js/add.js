$(document).ready(function() {
    $('#openModalButton').on('click', function() {
        $('#modal').show();
    });

    $('#addButton').on('click', function() {
        const type = $('#typeInput').val();
        const num = $('#numberInput').val();
        const price = parseInt($('#priceInput').val());

        if (type.trim() === '' || num.trim() === '' || isNaN(price)) {
            alert('Пожалуйста, заполните все поля корректно.');
            return;
        }

        $.ajax({
            url: '/add-data',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({number: num, price: price, type: type}),
        }).done(function() {
            alert('Данные успешно добавлены');
            $('#modal').hide();
            location.reload();
        }).fail(function() {
            alert('Ошибка добавления. Попробуйте еще раз.');
        });
    });
});
