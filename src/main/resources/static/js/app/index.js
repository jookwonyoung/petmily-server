var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {
        var data = {
            postImg: $('#postImg').val(),
            postContent: $('#postContent').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/post/save',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
            var year = $('#year').val();
            var month = $('#month').val();
            var day = $('#day').val();

            $.ajax({
                type: 'DELETE',
                url: '/api/walk/delete/'+year+"/"+month+"/"+day,
                dataType: 'text',
                contentType:'application/json; charset=utf-8'
            }).done(function() {
                alert('산책이 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
};

main.init();