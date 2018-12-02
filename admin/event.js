$(document).ready(function () {
	var uniqueId; 

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
    const eventRefObject = database.ref().child("event");  
    const categoryRefObject = database.ref().child("category");    

    categoryRefObject.on("value", snap => {
        console.log(snap.val());        
        const data = snap.val();
        data.forEach(function(e) {
            $('#category').append('<option value=' + e.category_id + '>' + e.category_name + '</option>')
        });
    });

    eventRefObject.on("value", snap => {
        uniqueId = snap.numChildren();
        console.log(snap.val());
    });    
   
    eventRefObject.on("child_changed", function(data) {
        console.log(data);        
    });

    function insert_event(attendingCount, categoryId, comment, cost, description, endTime, eventId, eventName, location, lat, lon, startTime, user) {
        database.ref('event/' + eventId).set({
            attending_count : attendingCount,
            category_id : categoryId,
            comment : comment,
            cost : cost,
            description : description,
            end_time : endTime,
            event_id : eventId,
            event_name : eventName,
            image : "https://firebasestorage.googleapis.com/v0/b/dana-event.appspot.com/o/1.jpg?alt=media&token=0da61a40-9627-4eae-b120-3ab7e1ebe744", 
            lat : lat, 
            location : location, 
            lon : lon,
            start_time : startTime,
            user : user,                 
        });
        window.location.href = "event.html";
    }

    $("#btnSubmit").click(function () {
        const eventId = uniqueId + 1;
        const eventName = $("#eventName").val();
        const attendingCount = parseInt($("#attendingCount").val());
        const categoryId = parseInt($("#category").val());
        const cost = parseInt($("#cost").val());
        const description = $("#description").val();
        const endTime = $("#endTime").val();
        if (!eventId || !eventName || !attendingCount || !attendingCount || !categoryId) {
            alert("All of field is requirement");
        }
        const user = [
            { "user_id" : 1, "status": "attending" },
            { "user_id" : 2, "status": "attending" }
        ];
        const comment = [
            { "user_id" : 1, "comment": "hấp dẫn" },
            { "user_id" : 2, "status": "thú vị" }
        ];
        const location = $("#location").val();
        const startTime = $("#startTime").val();
        const lat = parseFloat($("#lat").val());
        const lon = parseFloat($("#lon").val());

        insert_event(attendingCount, categoryId, comment, cost, description, endTime, eventId, eventName, location, lat, lon, startTime, user);
    });
});






