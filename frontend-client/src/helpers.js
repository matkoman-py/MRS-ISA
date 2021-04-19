const isObjectEmpty = (obj) => obj == null || obj == undefined || Object.keys(obj).length === 0;

export default { isObjectEmpty };