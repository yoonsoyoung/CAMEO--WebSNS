<template>
  <div class="container">
        <!-- <div class="row">
            <div class="col-md-12">
                <form class="form-inline" id="sendForm">
                    <div class="form-group">
                        <label for="message">Message</label>
                        <input type="text" id="message" 
                            class="form-control" 
                            placeholder="Enter your message here..."
                            v-model="message">
                    </div>
                    <button id="send" class="btn btn-default" type="button" @click="sendMessage">Send</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="message-history" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Messages</th>
                    </tr>
                    </thead>
                    <tbody id="messages">
                        <tr v-for="(item, index) in messages" :key="index">
                            <td>{{item}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="aa">
            <form action="/insertImage" enctype="multipart/form-data" method="post">
                <input type="file" name="imgFile" />
                <input type="submit" value="이미지저장" />
            </form>
        </div> -->
    </div>
</template>

<script>
// import Stomp from 'webstomp-client'
// import SockJS from 'sockjs-client'
export default {
created() {
    console.log("Index page is ready");
    this.connect();
},
updated() {
},
methods: {
    // connect() {
    //         var socket = new SockJS('/our-websocket');

    //         this.stompClient = Stomp.over(socket);
    //         this.stompClient.connect({}, function (frame) {
    //             console.log('Connected: ' + frame);
    //             this.stompClient.subscribe('/topic/messages', function (message) {
    //                 this.showMessage(JSON.parse(message.body).content);
    //             });
    //         });
    //     },
    //     showMessage(message) {
    //         // var msg = document.querySelector("#messages");
    //         // msg.append("<tr><td>" + message + "</td></tr>");
    //         this.messages.push(message);
    //     },
    //     sendMessage() {
    //         console.log("sending message");
    //         this.stompClient.send("/ws/message", {}, JSON.stringify({'messageContent': this.message}));
    //     },
},
data() {
    return {
        messages:[],
        message:'',
        stompClient:null,
    }
},
}
</script>

<style scoped>
.container {
    position: absolute;
    top: 300px;
}
.aa {
    position: relative;
    top: 200px;
}
</style>