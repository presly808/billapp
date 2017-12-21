<template>
    <f7-page hide-bars-on-scroll=''>
        <f7-navbar back-link='Back' title='Registration' sliding=''>
        </f7-navbar>
        <f7-list form>
            <!-- Text Input -->
            <f7-list-item>
                <f7-label>Phone</f7-label>
                <f7-input type="text" v-model="phone" placeholder="380(XX)-XXX-XX-XX"/>
                <p style="color: #f00" v-show="!validation.phone">Invalid phone</p>
            </f7-list-item>

            <!-- Password -->
            <f7-list-item>
                <f7-label>Password</f7-label>
                <f7-input type="password" v-model="password" placeholder="Password"/>
            </f7-list-item>

            <!-- Switch -->
            <f7-list-item>
                <f7-label>Company register</f7-label>
                <f7-input checked="false" type="switch" @input="onSwitch"></f7-input>
            </f7-list-item>

            <f7-list-item v-if="showCompanyBar">
                <f7-label>Company name</f7-label>
                <f7-input type="text" v-model="companyName" placeholder="CompanyName"/>
            </f7-list-item>

            <f7-list-item v-if="showCompanyBar">
                <f7-label>Address</f7-label>
                <f7-input type="text" v-model="address.city" placeholder="City"/>
                <f7-input type="text" v-model="address.street" placeholder="Street"/>
                <f7-input type="text" v-model="address.number" placeholder="Number"/>
            </f7-list-item>

            <f7-list-item v-if="showCompanyBar">
                <f7-label>Activity</f7-label>
                <f7-input type="text" v-model="activity" placeholder="Type of activity"/>
            </f7-list-item>


            <f7-list-item v-if="showCompanyBar">
                <f7-label>Additional Info</f7-label>
                <f7-input type="textarea" v-model="additionalInfo" placeholder="Additional"></f7-input>
            </f7-list-item>
        </f7-list>
            <!--todo not work keyUp-->
        <f7-button @click="register" @keyup.enter="register">Register</f7-button>
    </f7-page>
</template>

<script>


    export default {
        name: "registration",
        data() {
            return {
                showCompanyBar: false,
                phone: "",
                password: '',
                companyName: "",
                //todo зробити 3 окремих філда на адрес
                address: {
                    city: "",
                    street: "",
                    number: ""
                },
                activity: "",
                additionalInfo: ""

            }
        },
        methods: {
            onSwitch: function () {
                this.showCompanyBar = !this.showCompanyBar;
                console.log(this.showCompanyBar);
            },

            register: function () {
                if (this.isValid) {
                    $.ajax({
                            method: "POST",
                            url: "http://localhost:8080/create-user",
                            data: JSON.stringify(this.$data),
                            contentType: "application/json"
                        }
                    );
                    //todo не стирається з інпутів ніхера
                    this.phone = "";
                    this.password = "";
                    this.companyName = "";
                    this.address = {
                        city: "",
                        street: "",
                        number: ""
                    };
                    this.activity = "";
                    this.additionalInfo = "";

                    console.log("ok");
                } else {
                    console.log("ne ok");
                }
            }
        },
        computed: {
            //todo прикрутити нормально ерори на картинці
            //todo доробити валідатори
            validation: function () {
                return {
                    phone: /^\d{12}$/.test(this.phone)
                }
            },
            isValid: function () {
                var validation = this.validation;
                return Object.keys(validation).every(function (key) {
                    return validation[key]
                })
            }
        }


//todo не бачить стилів
    }
</script>

<style lang="sass?indentedSyntax">

</style>