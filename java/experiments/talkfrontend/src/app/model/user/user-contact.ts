export class UserContact {
    private _iduser: number;
    private _username: string;
    private _contact: boolean;

    constructor(iduser: number, username: string, contact: boolean) {
        this._iduser = iduser;
        this._username = username;
        this._contact = contact;
    }

    set iduser(iduser: number) {
        this._iduser = iduser;
    }

    set username(username: string) {
        this._username = username;
    }

    set contact(contact: boolean) {
        this._contact = contact;
    }

}
