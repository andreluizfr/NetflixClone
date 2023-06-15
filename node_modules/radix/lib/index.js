
exports.parse = function(numString) {
	return new exports.Number(numString);
};

exports.Number = function(numString) {
	this.radix      = 10;
	this.numString  = String(numString);
	this.parsed     = Number(numString);
};

exports.Number.prototype.asBase = function(radix) {
	this.radix = radix;
	this.parsed = parseFloat(this.numString, radix);
};

exports.Number.prototype.convertToBase = function(radix) {
	return this.parsed.toString(radix);
};

// -------------------------------------------------------------
//  Shortcuts

exports.Number.prototype.asDecimal =
exports.Number.prototype.asDec = function() {
	return this.asBase(10);
};

exports.Number.prototype.convertToDecimal =
exports.Number.prototype.dec = function() {
	return this.convertToBase(10);
};

exports.Number.prototype.asBinary =
exports.Number.prototype.asBin = function() {
	return this.asBase(2);
};

exports.Number.prototype.convertToBinary =
exports.Number.prototype.bin = function() {
	return this.convertToBase(2);
};

exports.Number.prototype.asOctal =
exports.Number.prototype.asOct = function() {
	return this.asBase(8);
};

exports.Number.prototype.convertToOctal =
exports.Number.prototype.oct = function() {
	return this.convertToBase(8);
};

exports.Number.prototype.asHexadecimal =
exports.Number.prototype.asHexdec = function() {
	return this.asBase(16);
};

exports.Number.prototype.convertToHexadecimal =
exports.Number.prototype.hexdec = function() {
	return this.convertToBase(16);
};
