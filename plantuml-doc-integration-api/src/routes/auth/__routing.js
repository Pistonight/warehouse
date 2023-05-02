import _new from './new.js';
import _token from './token.js';
import express from 'express';
const _auth = express.Router();
_new(_auth);
_token(_auth);
export default _auth;