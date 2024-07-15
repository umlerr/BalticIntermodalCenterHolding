$(document).ready(function() {
    $('#addButton').click(function() {
        $('#numberInputEdit').val('');
        $('#priceInputEdit').val('');
        $('#typeInputEdit').val('');

        $('#editModal').css('display', 'block');
    });

    $('.close').click(function() {
        $('#editModal').css('display', 'none');
    });

    $('#saveButton').click(function() {
        const newNumber = $('#numberInputEdit').val();
        const newPrice = $('#priceInputEdit').val();
        const newType = $('#typeInputEdit').val();

        $.ajax({
            url: '/add-row',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                number: newNumber,
                price: newPrice,
                type: newType
            })
        })
            .done(function(response) {
                const id = response.id;
                const newRow = `
                <tr data-id="${id}">
                    <td class="number">${newNumber}</td>
                    <td class="price">${newPrice}</td>
                    <td class="type">${newType}</td>
                </tr>
            `;
                $('table tbody').append(newRow);
                $('#editModal').css('display', 'none');
            })
            .fail(function(xhr, status, error) {
                console.error('Ошибка добавления. Попробуйте еще раз.');
                console.log(xhr, status, error);
            });
    });
});