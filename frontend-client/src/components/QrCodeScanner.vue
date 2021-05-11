<template>
    <div>
        {{qrResult}}
         <form @submit="scanImage">
            <label for="img">Select image:</label>
            <input ref="qrContainer" type="file" id="img" name="img" accept="image/*">
            <input type="submit" value="Get Drugstores">
        </form> 
    </div>
</template>

<script>
    import QrScanner from 'qr-scanner';
    import QrScannerWorkerPath from '!!file-loader!../../node_modules/qr-scanner/qr-scanner-worker.min.js';
    QrScanner.WORKER_PATH = QrScannerWorkerPath;

    export default {
        name: "QrCodeScanner",
        components: {
            
        },
        computed: {

        },

        data: function () {
            return {
                drugstores: [],
                qrResult: {},
            }
        },
        methods: {
            scanImage: function(event){
                event.preventDefault();
                const image = this.$refs['qrContainer'].files[0];
                QrScanner.scanImage(image)
                .then(result => 
                {
                    this.qrResult = this.parseResult(result);
                    this.$emit("qr-code-scanned", this.qrResult);
                })
                .catch(error => console.log(error || 'No QR code found.'));
            },
            parseResult: function(qrCode){
                const parts = JSON.parse(qrCode).split(";");
                const result = {};
                for(let part of parts){
                    const [key, amount] = part.split(',');
                    result[key] = amount;
                }
                return result;
            }
        },
    }
</script>