<template>
    <f7-page hide-bars-on-scroll=''>
        <f7-navbar back-link='Back' title='Registration' sliding=''>
        </f7-navbar>

        <f7-list form>
            <!-- Text Input -->
            <f7-list-item>
                <f7-label>Phone</f7-label>
                <f7-input type="text" v-model="phone" placeholder="380(XX)-XXX-XX-XX"/>
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
        <f7-button @click="register">Register</f7-button>


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
                    console.log("ok");
                } else {
                    // //todo other validation
                    if (!this.validation.phone) {
                        this.$f7.alert("Sorry! " + this.phone +
                            " is not valid. Must be 380XXXXXXXXX");
                    }
                    if (!this.validation.password) {
                        this.$f7.alert("Sorry! " + this.password +
                            " is not valid. Pass can`t start from gap, and must have length 4-12");
                    }
                    if (!this.validation.companyName) {
                        this.$f7.alert("Sorry! " + this.companyName +
                            " is not valid. Must have length 1-15");
                    }
                    if (!this.validation.activity) {
                        this.$f7.alert("Sorry! " + this.activity +
                            " is not valid. It must bee word");
                    }
                }
            },

        },
        computed: {

            validation: function () {
                return {
                    phone: /^[380]\d{11}$/.test(this.phone),
                    password: /^[^\s]{4,12}$/.test(this.password),
                    companyName: !this.showCompanyBar ? true : /^\w{1,15}$/.test(this.companyName),
                    activity: !this.showCompanyBar ? true : /^\w$/.test(this.activity)
                }
            },
            isValid: function () {
                var validation = this.validation;
                return Object.keys(validation).every(function (key) {
                    return validation[key]
                })
            }
        }
    }
</script>

<style lang="sass?indentedSyntax">

</style>