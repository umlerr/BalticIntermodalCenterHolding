$(document).ready(function() {
    let isCtrlPressed = false;

    $(document).keydown(function(event) {
        if (event.which === 17) { // Клавиша Ctrl
            isCtrlPressed = true;
        }
    }).keyup(function(event) {
        if (event.which === 17) {
            isCtrlPressed = false;
        }
    });

    $('tr').click(function(event) {
        if (!$(this).is(':first-child')) {
            if (isCtrlPressed) {
                $(this).toggleClass('selected');
            } else {
                if (!event.shiftKey) {
                    $('.selected').removeClass('selected');
                }
                $(this).addClass('selected');
            }
        }
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

    // Предотвращаем выбор заголовка таблицы
    $('th').click(function(event) {
        event.stopPropagation();
    });
});