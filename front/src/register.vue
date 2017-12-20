<template>
    <f7-page hide-bars-on-scroll=''>
    <f7-navbar back-link='Back' title='Registration' sliding='' >
        </f7-navbar>
        <f7-list form>
            <!-- Text Input -->
            <f7-list-item>
                <f7-label>Phone</f7-label>
                <f7-input type="text" v-model="phone" placeholder="(XXX)-XXX-XX-XX"/>
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
                <f7-input type="text" v-model="address" placeholder="Address"/>
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
                address: "",
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
                $.ajax({
                    method: "CREATE",
                    url: "/create-user",
                    data: JSON.stringify(this.data)
                    }

                )
            }
        },
        computed:{

        }
    }
</script>

<style lang="sass?indentedSyntax">
    /* Add your styles here */
</style>