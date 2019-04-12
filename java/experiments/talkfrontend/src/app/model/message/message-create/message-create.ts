export class MessageCreate {
    conversation_idconversation: number;
    user_iduser: number;
    text: string;

    constructor(
        conversation_idconversation: number,
        user_iduser: number,
        text: string
    ) {
        this.conversation_idconversation = conversation_idconversation;
        this.user_iduser = user_iduser;
        this.text = text;
    }

}
