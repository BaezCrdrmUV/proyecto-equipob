import express from 'express';
import { microservicioAccount } from '../clients/http/account.js';

const router = express.Router();

router.get("/getAccount", async (req, res) => {
    const { username, id } = req.query;

    microservicioAccount.GetAccount(username, id)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Account/getAccount", error);
    })
})

router.post("/login", async (req, res) => {
    microservicioAccount.Login(req.body)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Account/login", error);
    })
})

router.post("/loginMobile", async (req, res) => {
    let password = req.body.PasswordString;
    req.body.PasswordString = hashText(password);
    microservicioAccount.Login(req.body)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Account/loginMovile", error);
    })
})

    router.post("/registerNewAccountMobile", async(req, res) =>{
        let passwordNew = req.body.Passwords[0].PasswordString;
        req.body.Passwords[0].PasswordString = hashText(passwordNew);
        microservicioAccount.RegisterNewAccount(req.body)
        .then(values => {
            res.send(values);
        })
        .catch(error => {
            res.send("Account/register",error);
        })
    })

router.post("/registerNewAccount", async(req, res) =>{
    microservicioAccount.RegisterNewAccount(req.body)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Account/register",error);
    })
})

router.put("/updateAccount", async(req, res) =>{
    microservicioAccount.UpdateAccount(req.body)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Account/updateAccount",error);
    })

    
})

router.put("/banAccount", async(req, res) =>{
    const { accountId } = req.query;
    microservicioAccount.BanAccount(accountId)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Account/banAccount",error);
    })
})


router.put("/updatePassword", async(req, res) =>{
    microservicioAccount.UpdatePassword(req.body)
    .then(values => {
        res.send(values);
    })
    .catch(error => {
        res.send("Password/updatePassword",error);
    })
})



function hashText(text) 
{
    if (typeof text != 'string') {
        throw TypeError('El argumento debe ser una cadena de caracteres.');
    }

    if (!text.length) {
        return null;
    }

    let caracteres = text.split('');

    return caracteres.reduce((h, c) => (h = c.charCodeAt(0) + (h << 6) + (h << 16) - h), 0);
}

export default router;