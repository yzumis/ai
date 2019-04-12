export class Message {
    idmessage: number;
    conversation_idconversation: number;
    user_iduser: number;
    text: string;
    timestamp: number;

    constructor(
        idmessage: number,
        conversation_idconversation: number,
        user_iduser: number,
        text: string,
        timestamp: number
    ) {
        this.idmessage = idmessage;
        this.conversation_idconversation = conversation_idconversation;
        this.user_iduser = user_iduser;
        this.text = text;
        this.timestamp = timestamp;
    }

}
