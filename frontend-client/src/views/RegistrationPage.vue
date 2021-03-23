<template>
    <div>
        <h1>Registration form</h1>
        <b-container style="width:30%" fluid>
            <b-form @submit="onSubmit" v-if="show">
                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Email address:" label-for="input1">
                            <b-form-input id="input1" v-model="form.email" type="email" placeholder="Enter email"
                                required></b-form-input>
                        </b-form-group>
                    </b-col>
                </b-form-row>
                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Your Name:" label-for="input2">
                            <b-form-input id="input2" v-model="form.name" placeholder="Enter name" required>
                            </b-form-input>
                        </b-form-group>
                    </b-col>
                </b-form-row>
                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Your Surname:" label-for="input3">
                            <b-form-input id="input3" v-model="form.surname" placeholder="Enter surname" required>
                            </b-form-input>
                        </b-form-group>
                    </b-col>
                </b-form-row>
                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Your Password:" label-for="input4">
                            <b-form-input id="input4" v-model="form.password" placeholder="Enter password"
                                type="password" required>
                            </b-form-input>
                        </b-form-group>
                    </b-col>
                </b-form-row>
                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Your Phone Number:" label-for="input5">
                            <b-form-input id="input5" v-model="form.phoneNumber" placeholder="Enter Phone Number"
                                type="number" required></b-form-input>

                            <b-popover target="input5" id="pop" triggers="a" :placement="placement">
                                <p>
                                    <b-icon style="position: relative; top:8px" class="h4" name="icon"
                                        icon="exclamation-square-fill" variant="warning"></b-icon> Phone number must
                                    have at least 6 digits.
                                </p>
                            </b-popover>
                        </b-form-group>
                    </b-col>
                </b-form-row>

                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Choose your Country:" label-for="input6">
                            <b-form-input v-model="form.country" required list="my-list-id" placeholder="Enter country">
                            </b-form-input>

                            <datalist id="my-list-id">
                                <!-- <option>Manual Option</option> -->
                                <option v-bind:key="country" v-for="country in countries">{{ country }}</option>
                            </datalist>
                        </b-form-group>
                    </b-col>
                </b-form-row>

                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Your City:" label-for="input7">
                            <b-form-input id="input7" v-model="form.city" placeholder="Enter city" required>
                            </b-form-input>
                        </b-form-group>
                    </b-col>
                </b-form-row>

                <b-form-row>
                    <b-col>
                        <b-form-group id="inputg1" label="Your Address:" label-for="input8">
                            <b-form-input id="input8" v-model="form.address" placeholder="Enter address" required>
                            </b-form-input>
                        </b-form-group>
                    </b-col>
                </b-form-row>

                <b-button type="submit" variant="primary">Submit</b-button>
            </b-form>
        </b-container>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    email: '',
                    password: '',
                    name: '',
                    surname: '',
                    phoneNumber: '',
                    country: '',
                    city: '',
                    address: '',
                },
                phoneNumberError: '',
                placement: 'bottomright',
                show: true,
                countries: ["Afghanistan", "Albania", "Serbia", "Algeria", "American Samoa", "Andorra", "Angola",
                    "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                    "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                    "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana",
                    "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria",
                    "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
                    "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
                    "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
                    "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
                    "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
                    "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
                    "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji",
                    "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia",
                    "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
                    "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
                    "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras",
                    "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq",
                    "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
                    "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan",
                    "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
                    "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
                    "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives",
                    "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte",
                    "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia",
                    "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands",
                    "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
                    "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",
                    "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal",
                    "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
                    "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa",
                    "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone",
                    "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia",
                    "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka",
                    "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands",
                    "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China",
                    "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga",
                    "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
                    "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
                    "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela",
                    "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands",
                    "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"
                ]

            }
        },
        methods: {
            onSubmit(event) {
                this.$root.$emit('bv::hide::popover', 'pop')
                if (!this.validatePhoneNumber()) {
                    this.$root.$emit('bv::show::popover', 'pop')
                    return
                }
                event.preventDefault()
                alert(JSON.stringify(this.form))
            },
            validatePhoneNumber: function () {
                if (this.form.phoneNumber.length < 6) return false
                return true
            }
        }
    }
</script>

<style>

</style>