export class UserContact {
    iduser: number;
    username: string;
    contact: boolean;

    constructor(iduser: number, username: string, contact: boolean) {
        this.iduser = iduser;
        this.username = username;
        this.contact = contact;
    }

}
