const apiCall = ({commit, url, method, urlservice, mutation, ...args})=>{
    fetch(url + urlservice, { method: method })
        .then(r => r.json())
        .then(r => {
            commit(mutation, r)
        })
}

export default apiCall