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

    const preObject = document.getElementById("object");
    const dbRefObject = database.ref().child("user");

    // get data
    dbRefObject.on("value", snap => {
        preObject.innerText = JSON.stringify(snap.val(), null, 4);

    });

    $("#btnClick").click(function () {
       insert_user(55, "dung", "admin", "admin@gmail.com", "123");
    });

    // Retrieve new posts as they are added to our database
    dbRefObject.on("child_changed", function(data) {
        console.log(data);
        preObject.innerText = JSON.stringify(data.val(), null, 10);
    });

    function insert_user(userId, username, role, email, password) {
        database.ref('user/' + userId).set({
            username : username,
            role : role,
            email : email,
            password : password
        });
    }
});






