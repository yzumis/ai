export class User {
    private _iduser: number;
    private _username: string;
    private _token: string;

    constructor(iduser: number, username: string, token: string) {
        this._iduser = iduser;
        this._username = username;
        this._token = token;
    }

    set iduser(iduser: number) {
        this._iduser = iduser;
    }

    set username(username: string) {
        this._username = username;
    }

    set token(token: string) {
        this._token = token;
    }

}
