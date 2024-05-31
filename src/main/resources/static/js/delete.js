$(document).ready(function() {
    $('tr').click(function() {
        $(this).toggleClass('selected');
    });
    $('#deleteButton').click(function() {
        const selectedRows = $('.selected');

        if (selectedRows.length === 0) {
            alert('Выберите записи для удаления');
            return;
        }

        if (confirm('Вы уверены что хотите удалить эти записи?')) {
            const ids = [];
            selectedRows.each(function() {
                ids.push($(this).data('id'));
            });
            $.ajax({
                url: '/delete-rows',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(ids)
            }).done(function() {
                location.reload();
            }).fail(function() {
                alert('Ошибка удаления. Попробуйте еще раз.');
            });
        }
    });
});