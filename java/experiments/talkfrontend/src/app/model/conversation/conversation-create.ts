export class ConversationCreate {
    idUser: number;
    idUserContact: number;

    constructor(idUser: number, idUserContact: number) {
        this.idUser = idUser;
        this.idUserContact = idUserContact;
    }

}
