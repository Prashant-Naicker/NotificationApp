package main

import (
    "net/http"
    "io/ioutil"
    "encoding/json"
    "fmt"
)

type Message struct { Message string `json:"message"` }

var storage = []Message{}

func main() {
    http.HandleFunc("/send-message", message)
    http.HandleFunc("/send-notification", notification)
    http.ListenAndServe(":8080", nil)
}

func message(w http.ResponseWriter, r *http.Request) {
    SetGeneralHeaders(w)
    if r.Method == "OPTIONS" {
        w.WriteHeader(http.StatusOK)
        w.Write([]byte(""))
        return
    }
    fmt.Println("Message Request Made")

    message := Message{}

    //Reading request body.
    defer r.Body.Close()
    body, err := ioutil.ReadAll(r.Body)
    if err != nil {
        return
        fmt.Println(err)
    }

    //Unmarshalling the JSON object and storing it in the Message struct.
    err = json.Unmarshal(body, &message)
    if err != nil {
        return
        fmt.Println(err)
    }

    storage = append(storage, message)
    fmt.Println(storage)

    w.Write([]byte(`{"message": "Message has been recorded."}`))

    return
}

func notification(w http.ResponseWriter, r *http.Request) {
    SetGeneralHeaders(w)
    fmt.Println(storage)

    fmt.Println("Notification Request Made")

    b, err := json.Marshal(storage)
    if err != nil { return }

    storage = []Message{}
    fmt.Println(storage)
    w.Write(b)

}

func SetGeneralHeaders(w http.ResponseWriter) {
    w.Header().Set("Cache-Control", "no-store")
    w.Header().Set("Access-Control-Allow-Origin", "*")
    w.Header().Set("Access-Control-Allow-Methods", "POST, OPTIONS")
    w.Header().Set("Access-Control-Allow-Headers", "Content-Type, Accept, Origin")
}
