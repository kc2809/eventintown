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

    eventRefObject.on("value", snap => {
        uniqueId = snap.numChildren();
        console.log(snap.val());
    });    
   
    eventRefObject.on("child_changed", function(data) {
        console.log(data);        
    });

    function insert_event(attendingCount, categoryId, comment, cost, description, endTime, eventId, eventName, location, startTime, user) {
        database.ref('event/' + eventId).set({
            attending_count : attendingCount,
            category_id : categoryId,
            comment : comment, 
            cost : cost, 
            description : description,
            end_time : endTime,             
            event_id : eventId,
            event_name : eventName,
            // image : image, 
            // lat : lat, 
            location : location, 
            // lon : lon,
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

        insert_event(attendingCount, categoryId, comment, cost, description, endTime, eventId, eventName, location, startTime, user);
    });
});






