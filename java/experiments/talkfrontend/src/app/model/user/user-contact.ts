export class UserContact {
    iduser: number;
    username: string;
    contact: boolean;
    pendingMessages: boolean

    constructor(iduser: number, username: string, contact: boolean, pendingMessages: boolean) {
        this.iduser = iduser;
        this.username = username;
        this.contact = contact;
        this.pendingMessages = pendingMessages;
    }

}
