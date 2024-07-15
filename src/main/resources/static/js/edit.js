$(document).ready(function() {
    var $tableRows = $('tr');

    $tableRows.click(function() {
        $tableRows.removeClass('selected');
        $(this).addClass('selected');
    });

    $('#editButton').click(function() {
        const selectedRow = $tableRows.filter('.selected');

        if (selectedRow.length === 0) {
            alert('Выберите запись для редактирования');
            return;
        }

        const number = selectedRow.find('.number').text();
        const price = selectedRow.find('.price').text();
        const type = selectedRow.find('.type').text();
        $('#currentValueNumber').text(number);
        $('#currentValuePrice').text(price);
        $('#currentValueType').text(type);

        $('#numberInputEdit').val(number);
        $('#priceInputEdit').val(price);
        $('#typeInputEdit').val(type);

        $('#editModal').css('display', 'block');
    });

    $('.close').click(function() {
        $('#editModal').css('display', 'none');
    });

    $('#saveButton').click(function() {
        const selectedRow = $tableRows.filter('.selected');
        const id = selectedRow.data('id');
        const newNumber = $('#numberInputEdit').val();
        const newPrice = $('#priceInputEdit').val();
        const newType = $('#typeInputEdit').val();

        $.ajax({
            url: '/update-row',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: id,
                number: newNumber,
                price: newPrice,
                type: newType
            })
        })
            .done(function() {
                selectedRow.find('.number').text(newNumber);
                selectedRow.find('.price').text(newPrice);
                selectedRow.find('.type').text(newType);
                $('#editModal').css('display', 'none');
                location.reload();
            })
            .fail(function(xhr, status, error) {
                console.error('Ошибка обновления. Попробуйте еще раз.');
                console.log(xhr, status, error);
            });
    });
});