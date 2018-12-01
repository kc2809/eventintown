$(document).ready(function () {
    const config = {
        apiKey: "AIzaSyD4anhA5e7OK-ytLJbMjyXnudTsqRtGeHE",
        authDomain: "dana-event.firebaseapp.com",
        databaseURL: "https://dana-event.firebaseio.com",
        projectId: "dana-event",
        storageBucket: "dana-event.appspot.com",
        messagingSenderId: "219297288084"
    };
    firebase.initializeApp(config);
    var database = firebase.database();

    const dbRefObject = database.ref().child("event");

    // get data
    dbRefObject.on("value", snap => {
        const data = snap.val();
        let html = "";
        data.forEach(function(element) {
            html += body_tab(element);
        });
        $( "#tbody" ).empty();
        $("#tbody").append(html);
    });

    const body_tab = (data) => {
        return '<tr role="row" class="odd">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td class="sorting_1">'+data.event_id+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td><img src="./assets/img/user.png" class="avatar" alt="Avatar">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.event_name+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.location+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.attending_count+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.start_time+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.end_time+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span data-toggle="2" class="badge  label-danger">blocked</span>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href="javascript:void(0)"\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t   class="btn btn-info btn-xs bg-light-blue user-edit"\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t   data-url="edit/2"><i class="fa fa-pencil"></i> Edit\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href="javascript:void(0)"\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t   class="btn btn-danger btn-xs user-delete" id="2"><i\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tclass="fa fa-lock"></i>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tUnblock </a>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t</tr>';
    }
});






