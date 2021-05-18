<template>
    <div>
        <form @submit="scanImage">
            <b-form-row>
                <b-col style="height: 300px">
                    <div id="preview">
                        <img
                            v-if="url"
                            :src="url"
                            height="300px"
                            width="300px"
                        />
                        <img
                            v-else
                            height="300px"
                            width="300px"
                            src="https://pest-mart.com/wp-content/plugins/woo-category-slider-by-pluginever/assets/images/placeholder.png"
                        />
                    </div>
                </b-col>
                <b-col>
                    <h3 style="text-align: left;">eReceipt Scanner</h3>
                    <b-form-row class="mt-3">
                        <label>Select image:</label>
                    </b-form-row>
                    <b-form-row class="mt-3">
                        <b-form-file
                            ref="qrContainer"
                            type="file"
                            id="img"
                            name="img"
                            accept="image/*"
                            variant="outline-hub"
                            @change="onImageChange"
                        ></b-form-file>
                    </b-form-row>
                    <b-form-row class="mt-3">
                        <b-button
                            v-b-toggle.collapse-search
                            variant="outline-hub"
                            >Advanced Search</b-button
                        >
                    </b-form-row>
                    <b-collapse id="collapse-search" class="mt-3">
                        <b-card>
                            <b-form-row>
                                <label for="nameInput">Name:</label>
                                <b-form-input
                                    id="nameInput"
                                    v-model="formData.drugstoreName"
                                    size="sm"
                                ></b-form-input>
                            </b-form-row>
                            <b-form-row class="mt-3">
                                <label for="cityInput">City:</label>
                                <b-form-input
                                    id="cityInput"
                                    size="sm"
                                    v-model="formData.drugstoreCity"
                                ></b-form-input>
                            </b-form-row>
                            <b-form-row class="mt-3">
                                <label for="minimumRatingInput"
                                    >Minimum Rating:</label
                                >
                                <b-form-rating
                                    size="sm"
                                    id="minimumRatingInput"
                                    variant="hub"
                                    min="0"
                                    max="10"
                                    v-model="formData.minimumRating"
                                    >5</b-form-rating
                                >
                            </b-form-row>
                            <b-form-row class="mt-3">
                                <label>Price:</label>
                                <b-col cols="3">
                                    <b-form-input
                                        type="number"
                                        class="ml-3"
                                        id="fromPrice"
                                        size="sm"
                                        v-model="formData.fromPrice"
                                    ></b-form-input>
                                </b-col>
                                <b-col
                                    cols="1"
                                    class="ml-3"
                                    align-self="center"
                                >
                                    <label> - </label>
                                </b-col>
                                <b-col cols="3">
                                    <b-form-input
                                        type="number"
                                        id="fromPrice"
                                        size="sm"
                                        v-model="formData.toPrice"
                                    ></b-form-input>
                                </b-col>
                            </b-form-row>
                        </b-card>
                    </b-collapse>
                    <b-form-row class="mt-3">
                        <b-button type="submit" variant="outline-hub">
                            Get Drugstores
                        </b-button>
                    </b-form-row>
                </b-col>
            </b-form-row>
        </form>
    </div>
</template>

<script>
import QrScanner from "qr-scanner";
import QrScannerWorkerPath from "!!file-loader!../../node_modules/qr-scanner/qr-scanner-worker.min.js";
QrScanner.WORKER_PATH = QrScannerWorkerPath;

export default {
    name: "QrCodeScanner",
    components: {},
    computed: {},

    data: function() {
        return {
            drugstores: [],
            qrResult: {},
            url: "",
            formData: {
                drugstoreName: "",
                minimumRating: 0,
                fromPrice: 0,
                toPrice: 0,
                drugstoreCity: "",
            },
        };
    },
    methods: {
        onImageChange: function(event) {
            const file = event.target.files[0];
            this.url = URL.createObjectURL(file);
        },
        scanImage: function(event) {
            event.preventDefault();
            const image = this.$refs["qrContainer"].files[0];
            QrScanner.scanImage(image)
                .then((result) => {
                    this.qrResult = this.parseResult(result);
                    this.$emit("qr-code-scanned", this.qrResult, this.formData);
                })
                .catch((error) => console.log(error || "No QR code found."));
        },
        parseResult: function(qrCode) {
            const parts = JSON.parse(qrCode).split(";");
            const result = {};
            for (let part of parts) {
                const [key, amount] = part.split(",");
                result[key] = amount;
            }
            return result;
        },
    },
};
</script>
