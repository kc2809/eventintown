$(document).ready(function () {
   $('.side-menu li').click(function () {
       $('.side-menu').children().removeClass('current-page');
   });
    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
    });
    
    $(document).on('change', '.select-country',function () {
        var country_id = $(this).val();
        var url = $(this).attr('data-url');
        //returnCity(country_id,url);
    });
    
    $.validator.addMethod("regex", function (value, element, regexpr) {
        return regexpr.test(value);
    }, "Please enter a valid this field.");

    // scroll top when click pagination
    $('.table').on( 'page.dt', function () {
        var top = $('.dataTables_wrapper').offset().top;
        $('html, body').animate({scrollTop: top},300);
    } );

    $(window).on('load',function(){
        var minheight = $('.left_col').innerHeight();
        $('.right_col').css({'min-height': minheight});
    });


    $('#filter-history').change(function () {
        var value = $(this).val();
        if (value == 'all'){
            window.location.href = '/admin/booking/list';
        }
        else{
            window.location.href = '/admin/booking/list/' + value;
        }
    });
    $('.viewAll-notify').click(function () {
        window.location.href = '/edit/notification';
        location.reload();
    });
});

/**
 * display bootstrap modal with content load by ajax
 * @param id
 */
function loadModalContent(id) {
    var url = id.attr('data-url');
    $.ajax({
        type: "GET",
        url: url,
        beforeSend: function () {
            $("body").append(overlay);
        },
        error: function () {
            $('.bg-overlay').remove();
        },
        success: function (data) {
            $('.modal-body').html(data);
            $('.modal').modal('show');
            $('.bg-overlay').remove();
            return false;
        },
        cache: false,
        async: false
    });
}

/**
 * display messi modal
 * @param m_title
 * @param m_class
 * @param message
 */
function modalDisplay(m_title, m_class, message) {
    new Messi(
        message,
        {
            modal: true,
            modalOpacity: 0.5,
            title: m_title,
            titleClass: m_class
        }
    );
}

/**
 * display messi error message modal
 * @param message
 * @returns {*}
 */
function modalError(message) {
    return modalDisplay('Error', 'error', message);
}

/**
 * display messi warning message modal
 * @param message
 * @returns {*}
 */
function modalWarning(message) {
    return modalDisplay('Warning', 'warning', message);
}

/**
 * display messi success message modal
 * @param message
 * @returns {*}
 */
function modalSuccess(message) {
    return modalDisplay('Success', 'success', message);
}

/**
 * display messi success message modal with redirect url
 * @param message
 * @param link
 */
function successPopup(message, link) {
    new Messi(
        message,
        {
            modal: true,
            modalOpacity: 0.5,
            title: 'Success',
            titleClass: 'success',
            buttons: [
                {id: 0, label: 'Yes', val: 'Y'}
            ],
            callback: function (val) {
                window.location.replace(link);
            }
        }
    );
}

/**
 * ajax change status
 * @param ajaxUrl
 * @param obj
 */
function changeStatus(ajaxUrl, obj) {
    var status_id = obj.attr('id');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: {id: status_id},
        dataType: "JSON",
        success: function (result) {
            if (result.success) {
                if (result.data == 'Enabled' || result.data == 'Active') {
                    obj.removeClass('label-danger').addClass('label-success');
                } else {
                    obj.removeClass('label-success').addClass('label-danger');
                }
                obj.text(result.data);
            } else {
                modalError(result.message);
            }
            return false;
        }
    });
}

/**
 * ajax remove multi records has checked
 * @param ids_value
 * @param message
 * @param ajaxUrl
 * @param redirectUrl
 */
function modalDelete(ids_value, message, ajaxUrl, redirectUrl) {
    new Messi(
        message,
        {
            modal: true,
            modalOpacity: 0.5,
            title: 'Confirmation',
            titleClass: 'warning',
            buttons: [
                {id: 0, label: 'Yes', val: 'Y'},
                {id: 1, label: 'No', val: 'N'}
            ],
            callback: function (val) {
                if (val == 'Y') {
                    // sent ajax to remove
                    ajaxSubmit(ajaxUrl, {ids: ids_value}, redirectUrl);
                }
            }
        }
    );
}

function ajaxSubmit(ajaxUrl, ajaxData, redirectUrl) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: ajaxData,
        dataType: "JSON",
        cache: false,
        async: false,
        beforeSend: function () {
            $("body").append(overlay);
        },
        error: function () {
            $('.bg-overlay').remove();
        },
        success: function () {
            $('.bg-overlay').remove();
            if (redirectUrl) {
                if (!redirectUrl) {
                    window.location.reload();
                } else {
                    window.location.replace(redirectUrl);
                }
            }
            
            return false;
        }
    });
}

$(document).ready(function () {
    /**
     * add csrf token for ajax request
     */
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });
    
    /**
     * re-style checkbox & radio inputs by iCheck
     */
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
    });
    
    $('input').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green',
        increaseArea: '20%'
    });
    
    /**
     * click to check all
     */
    $("#bulkDelete").on('ifClicked', function () {
        var status = this.checked;
        if (!status) {
            $(".deleteRow").iCheck('check');
        } else {
            $(".deleteRow").iCheck('uncheck');
        }
    });
    
    $('.admin-logout').on('click', function (e) {
        e.preventDefault();
        var url = this.dataset.url;
        $.ajax({
            method: 'POST',
            url: url,
            beforeSend: showLoader
        }).always(hideLoader)
            .done(function (e) {
                location.reload();
            })
    });
    $('.bell-notify').click(function () {
        var url = $(this).data('url');
        var $this = $(this);
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'JSON'
        }).done(function (str) {
            if (str.success) {
                $this.removeClass('bell-notify');
                $this.find('.badge').text('');
            } else {
            }
        }).fail(function (e) {
        }).always(function () {
        });
    });

    $(document).on('click','.mark-as-read',function () {
        var url = $(this).data('url');
        var $this = $(this);
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'JSON',
            data:{id:$this.data('noid')}
        }).done(function (str) {
            if (str.success) {
                $this.removeClass('mark-as-read');
                $this.find('.badge').text('');
            }
        }).fail(function (e) {
        }).always(function () {
        });
    });
});



function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function showLoader() {
    if ($(this).is('button')) {
        this.disabled = true;
        $(this).addClass('disabled');
    }
    $('.callout-error').html('');
    $('#loading').show();
}

function hideLoader() {
    if ($(this).is('button')) {
        this.disabled = false;
        $(this).removeClass('disabled');
    }
    $('#loading').hide();
}

function daysInMonth(m, y) {
    return m === 2 ? y & 3 || !(y % 25) && y & 15 ? 28 : 29 : 30 + (5546 >> m & 1);
}

String.prototype.toSlug = function () {
    st = this.toLowerCase();
    st = st.replace(/[\u00C0-\u00C5]/ig, 'a');
    st = st.replace(/[\u00C8-\u00CB]/ig, 'e');
    st = st.replace(/[\u00CC-\u00CF]/ig, 'i');
    st = st.replace(/[\u00D2-\u00D6]/ig, 'o');
    st = st.replace(/[\u00D9-\u00DC]/ig, 'u');
    st = st.replace(/[\u00D1]/ig, 'n');
    st = st.replace(/[^a-z0-9 ]+/gi, '');
    st = st.trim().replace(/ /g, '_');
    st = st.replace(/[\-\_]{2}/g, '');
    return (st.replace(/[^a-z\_ ]*/gi, ''));
};
function showErrorAlert(reason, detail) {
    var msg = '';
    if (reason === 'unsupported-file-type') {
        msg = "Unsupported format " + detail;
    } else {
        console.log("error uploading file", reason, detail);
    }
    $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>' +
        '<strong>File upload error</strong> ' + msg + ' </div>').prependTo('#alerts');
}

function addEditor() {
    $('.editor-wrapper').each(function () {
        var id = $(this).attr('id');//editor-one
        
        $(this).wysiwyg({
            toolbarSelector: '[data-target="#' + id + '"]',
            fileUploadError: showErrorAlert,
            hotKeys: {
                'ctrl+b meta+b': 'bold',
                'ctrl+i meta+i': 'italic',
                'ctrl+u meta+u': 'underline',
                'ctrl+z meta+z': 'undo',
                'ctrl+y meta+y meta+shift+z': 'redo'
            }
        });
    });
}

function tinyMce() {
    if (typeof(tinyMCE) != "undefined") {
        tinymce.EditorManager.editors = [];
    }
    tinymce.init({
        selector: ".editor-wrapper", theme: "modern",
        plugins: [
            "advlist autolink link image lists charmap print preview hr anchor pagebreak",
            "searchreplace wordcount visualblocks visualchars insertdatetime media nonbreaking",
            "table contextmenu directionality emoticons paste textcolor responsivefilemanager code"
        ],
        toolbar1: "undo redo | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | styleselect",
        toolbar2: "| responsivefilemanager | link unlink anchor | image media | forecolor backcolor  | print preview code ",
        image_advtab: true,
        
        external_filemanager_path: "/filemanager/",
        filemanager_title: "Responsive Filemanager",
        external_plugins: {"filemanager": "/filemanager/plugin.min.js"}
    });
}

function returnCity(country_id,url) {
    $.ajax({
        url: url,
        data:{country_id:country_id},
    }).done(function (e) {
        if(e.success){
            var st = '<option value="" disabled="" selected>Please select</option>';
            e.data.forEach(function (city) {
                st+=htmlCity(city);
            });
            $('.select-city').html(st);
        }
    }).always()
}

function htmlCity(data) {
    return '<option value="'+data['city_id']+'">'+data['city_name']+'</option>';
}

function addZero(num) {
    num = String(num);
    return num < 10 ? '0' + num : String(num);
}

function getDate(times) {
    if(!times)
        times = new Date();
    return times.getFullYear()+'/'+addZero(times.getMonth() + 1)+'/'+addZero(times.getDate());
}





