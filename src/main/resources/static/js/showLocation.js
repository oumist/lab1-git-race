const EINAcoords = [41.6835727,-0.8881165];
const coords = [41.6606, -0.87734];
const myProvider = new GeoSearch.OpenStreetMapProvider();

let map = L.map("map");
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

//Code from https://github.com/smeijer/leaflet-geosearch
//for adding search control with autocomplete option
new GeoSearch.GeoSearchControl({
    provider: myProvider, // required
    style: 'bar',
    showMarker: true, // optional: true|false  - default true
    showPopup: false, // optional: true|false  - default false
    marker: {
      // optional: L.Marker    - default L.Icon.Default
      icon: new L.Icon.Default(),
      draggable: false,
    },
    popupFormat: ({ query, result }) => result.label, // optional: function    - default returns result label
    maxMarkers: 1, // optional: number      - default 1
    retainZoomLevel: false, // optional: true|false  - default false
    animateZoom: true, // optional: true|false  - default true
    autoClose: true, // optional: true|false  - default false
    searchLabel: 'Do you want to go elsewhere?', // optional: string      - default 'Enter address'
    keepResult: false, // optional: true|false  - default false
}).addTo(map)

const marker = L.marker(
    EINAcoords
    ).bindPopup(
        "<b>EINA</b><br>Home is where your Wifi connects automatically.<br>Eduroam <3"
    )
    .addTo(map);
//Shows a map centered in Zaragoza
map.setView(EINAcoords, 13);
marker.openPopup();