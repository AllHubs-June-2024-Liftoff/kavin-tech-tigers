
    let map, center = { lat: 38.638132, lng: -90.282402 };
    let mapId = [[${mapId.ConfigValue}]];

async function initMap() {
    const { Map, InfoWindow } = await google.maps.importLibrary("maps");
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
    const rides = [[${rides}]];


    map = new Map(document.getElementById("map"), {
        center: center,
        zoom: 10,
        mapId: mapId,
    });

    const bikeLayer = new google.maps.BicyclingLayer();
    bikeLayer.setMap(map);

    const infoWindow = new InfoWindow();

    const activeRideSelector = new google.maps.marker.PinElement({
        scale: 1.5,
        background: "#36f136",
        borderColor: "#000000"
    });

    const pinRideSelector = new AdvancedMarkerElement({
        map,
        position: center,
        title: "Let's Bike!",
        gmpDraggable: true,
        content: activeRideSelector.element,
    });



    rides.forEach(ride => {
        const marker = new AdvancedMarkerElement({
                    position: { lat: ride.latitude, lng: ride.longitude },
                    map: map,
                    title: ride.userNameRideOwner,
                    })
            });

    pinRideSelector.addListener("dragend", (event) => {
        const position = pinRideSelector.position;
        infoWindow.close();
        infoWindow.setContent(`Select Where to Meet!: ${position.lat}, ${position.lng}`);
        infoWindow.open(pinRideSelector.map, pinRideSelector);
        document.getElementById("latitude").value = position.lat;
        document.getElementById("longitude").value = position.lng;
    });

}

window.onload = initMap;
