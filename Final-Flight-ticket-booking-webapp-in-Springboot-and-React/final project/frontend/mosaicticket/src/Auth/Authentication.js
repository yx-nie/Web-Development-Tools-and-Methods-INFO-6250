function Authentication(response){

    const token=response.data.token;

    sessionStorage.setItem('token', token);
}

export default Authentication