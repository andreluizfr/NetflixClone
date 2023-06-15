# radix

A Node.js module for converting numbers between different bases

## Install

```bash
$ npm install radix
```

## Usage

```javascript
var radix = require('radix');

var dec = radix.parse('FF').asBase(16).convertToBase(10);
```
