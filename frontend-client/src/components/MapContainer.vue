<template>
  <div ref="map-root"
       style="width: 100%; height: 100%">
  </div>
</template>
 
<script src='./libs/v6.3.1-dist/ol.js'></script>
<script>
  import View from 'ol/View'
  import Map from 'ol/Map'
  import TileLayer from 'ol/layer/Tile'
  import OSM from 'ol/source/OSM'
  import Feature from 'ol/Feature'
  import Point from 'ol/geom/Point'
  import {Style, Icon} from 'ol/style'

  // importing the OpenLayers stylesheet is required for having
  // good looking buttons!
  import 'ol/ol.css'

  import VectorLayer from 'ol/layer/Vector'
  import VectorSource from 'ol/source/Vector'
  import GeoJSON from 'ol/format/GeoJSON'

  import {fromLonLat, toLonLat} from 'ol/proj';


  const data = {
    type: 'Feature',
    properties: {},
    geometry: {
      type: 'Polygon',
      coordinates: [
        [
          [
            18.7559,
            46.265
          ],
          [
            23.05,
            46.265
          ],
          [
            23.05,
            41.85
          ],
          [
            18.7559,
            41.85
          ]
        ]
      ]
    }
  };


  export default {
    name: 'MapContainer',
    components: {},
    props: ["coordinates"],
    data: function() {
        return {
            olMap: null,
            vectorLayer: null,
            vectorSource: null,

            //dodato
            value: {
              street: "",
              city: "",
              country: ""
            }

        }
    },methods : {
        updateSource(geojson) {
            const view = this.olMap.getView()
            const source = this.vectorLayer.getSource()

            const features = new GeoJSON({
            featureProjection: 'EPSG:3857',
            }).readFeatures(geojson)

            source.clear();
            source.addFeatures(features);

            view.fit(source.getExtent())
      },
        addMarker(coordinates) {
            console.log(coordinates);
            var marker = new Feature(new Point(coordinates));
            var zIndex = 1;
            marker.setStyle(new Style({
            image: new Icon(({
                anchor: [0.5, 36], 
                anchorXUnits: "fraction",
                anchorYUnits: "pixels",
                opacity: 1,
                src: "static/mapPointer.png", 
                zIndex: zIndex,
            })),
            zIndex: zIndex
            }));
            this.vectorSource.clear();
            this.vectorSource.addFeature(marker);
            this.$parent.$parent.drugstore.point.longitude = toLonLat(coordinates)[0];
            this.$parent.$parent.drugstore.point.latitude = toLonLat(coordinates)[1];
            //dodato
            this.guessLocationFromCoordinates(toLonLat(coordinates)[0], toLonLat(coordinates)[1]);
        },


      guessLocationFromCoordinates: function(lon, lat) {
			this.$http.get("https://nominatim.openstreetmap.org/reverse", {
					params: {
						lat: lat,
						lon: lon,
						format: "json",
					},
				})
				.then((response) => {
					this.updateFieldsFromResponseData(response.data);
				})
				.catch(function(error) {
					//toastr.error('Could not find a location based on given coordinates.', '');
				});
		},
		
		updateFieldsFromResponseData: function(data) {
			const { address } = data;
			if (address) {
				if (address.street) {
					this.value.street = address.street;
          if (address["house-number"]) {
					this.value.street = this.value.street + ", " + address["house-number"];
          }
          else if (address["house_number"]) {
            this.value.street = this.value.street + ", " + address["house_number"];
          }
				}
        if (address.road) {
					this.value.street = address.road;
          if (address["house-number"]) {
					this.value.street = this.value.street + ", " + address["house-number"];
          }
          else if (address["house_number"]) {
            this.value.street = this.value.street + ", " + address["house_number"];
          }
				}
				if (address.town) {
					this.value.city = address.town;
          if (address.postCode) {
					this.value.city = this.value.city + ", " + address.postCode;
          }
          else if (address.postcode) {
            this.value.city = this.value.city + ", " + address.postcode;  
          }
				}
				else if (address.city) {
					this.value.city = address.city;
          if (address.postCode) {
					this.value.city = this.value.city + ", " + address.postCode;
          }
          else if (address.postcode) {
            this.value.city = this.value.city + ", " + address.postcode;  
          }
				}
        if (address.country) {
          this.value.country = address.country;
        }
      }
      this.$parent.$parent.drugstore.location.address = this.value.street;
      this.$parent.$parent.drugstore.location.city = this.value.city;
      this.$parent.$parent.drugstore.location.country = this.value.country;
		},


    }, watch: {
      geojson(value) {
        // call `updateSource` whenever the input changes as well
        this.updateSource(value)
      }
    },
    mounted() {
      this.vectorSource = new VectorSource({
          features: [],
        });
        this.vectorLayer = new VectorLayer({
        source: this.vectorSource
      })

        this.olMap = new Map({
        // the map will be created using the 'map-root' ref
        target: this.$refs['map-root'],
        layers: [
          // adding a background tiled layer
          new TileLayer({
            source: new OSM() // tiles are served by OpenStreetMap
          }),
          this.vectorLayer
        ],

        // the map view will initially show the whole world
        
        view: new View({
          zoom: 0,
          center: [0,0],
          constrainResolution: true
        }),
        
      })

        this.olMap.on('dblclick', (evt) => {
            this.addMarker(evt.coordinate);
        });

      this.updateSource(data);
      this.addMarker(fromLonLat(this.coordinates));
    },
  }
</script>