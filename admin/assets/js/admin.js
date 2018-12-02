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
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td><img style="width:100px; height: 100px;" src=' + data.image + 'class=avatar alt=Avatar>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.event_name+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.location+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.attending_count+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.cost+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.start_time+'</td>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t\t<td>'+data.end_time+'</td>\n' +
           
            '\t\t\t\t\t\t\t\t\t\t\t\t</tr>';
    }


});






