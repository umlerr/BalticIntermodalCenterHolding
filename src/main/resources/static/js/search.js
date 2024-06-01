$(document).ready(function() {
    $('#searchInput').on('input', function() {
        const keyword = $(this).val();
        $.ajax({
            type: 'GET',
            url: '/search',
            data: {
                keyword: keyword
            }
        }).done(function(response) {
            $('tbody').empty();
            if(response.length === 0) {
                alert('По вашему запросу ничего не найдено. Возвращаю на главную страницу.');
                window.location.href = '/';
            } else {
                response.forEach(function(coffee) {
                    const row = '<tr data-id="' + coffee.id + '"><td>' + coffee.id + '</td><td>' + coffee.name + '</td></tr>';
                    $('tbody').append(row);
                });
            }
        }).fail(function() {
            alert('Произошла ошибка при выполнении поиска. Пожалуйста, попробуйте снова.');
            window.location.href = '/';
        });
    });
});