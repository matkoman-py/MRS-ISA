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

            // this zooms the view on the created object
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
        }
    }, watch: {
      geojson(value) {
        // call `updateSource` whenever the input changes as well
        this.updateSource(value)
      }
    },
    mounted() {
      // this is where we create the OpenLayers map
      //const feature = new GeoJSON().readFeature(data, {
        // this is required since GeoJSON uses latitude/longitude,
        // but the map is rendered using “Web Mercator”
        //featureProjection: 'EPSG:3857'
     // });

      // a new vector layer is created with the feature
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
            //console.log(ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326'));
            //console.log(ol);
            this.addMarker(evt.coordinate);
        });

      this.updateSource(data);
      this.addMarker(fromLonLat(this.coordinates));
    },
  }
</script>