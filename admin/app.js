(function () {
    const config = {
        apiKey: "AIzaSyD4anhA5e7OK-ytLJbMjyXnudTsqRtGeHE",
        authDomain: "dana-event.firebaseapp.com",
        databaseURL: "https://dana-event.firebaseio.com",
        projectId: "dana-event",
        storageBucket: "dana-event.appspot.com",
        messagingSenderId: "219297288084"
    };
    firebase.initializeApp(config);

    const preObject = document.getElementById("object");

    const dbRefObject = firebase.database().ref().child("user");

    dbRefObject.on("value", snap => {
        preObject.innerText = JSON.stringify(snap.val(), null, 3);
    });

}());