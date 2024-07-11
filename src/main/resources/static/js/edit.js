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

        const name = selectedRow.find('.name').text();
        $('#currentValue').text(name);

        $('#nameInputEdit').val(name);

        $('#editModal').css('display', 'block');
    });

    $('.close').click(function() {
        $('#editModal').css('display', 'none');
    });

    $('#saveButton').click(function() {
        const newName = $('#nameInputEdit').val();
        const id = $('.selected').data('id');

        $.ajax({
            url: '/update-row',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                id: id,
                name: newName
            })
        })
            .done(function() {
                const selectedRow = $('.selected');
                selectedRow.find('.name').text(newName);
                $('#editModal').css('display', 'none');
                location.reload();
            })
            .fail(function(xhr, status, error) {
                console.error('Ошибка удаления. Попробуйте еще раз.');
                console.log(xhr, status, error);
            });
    });
});