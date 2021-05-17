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
                    this.$emit("qr-code-scanned", this.qrResult);
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
