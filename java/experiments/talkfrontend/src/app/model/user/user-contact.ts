export class UserContact {
    private _iduser: number;
    private _username: string;
    private _contact: boolean;

    constructor(iduser: number, username: string, contact: boolean) {
        this._iduser = iduser;
        this._username = username;
        this._contact = contact;
    }

    get iduser(): number {
        return this._iduser;
    }

    set iduser(iduser: number) {
        this._iduser = iduser;
    }

    get username(): string {
        return this._username;
    }

    set username(username: string) {
        this._username = username;
    }

    get contact(): boolean {
        return this._contact;
    }
    
    set contact(contact: boolean) {
        this._contact = contact;
    }

}
