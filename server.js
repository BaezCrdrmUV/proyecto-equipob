/*
 Date: 13/05/2021
 Author(s): Ricardo Moguel Sanchez
*/
import express from "express";
import audioRouter from "./routes/streaming.js";
import fileupload from "express-fileupload";
const app = express();
const PORT = 8083;

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(fileupload());
app.use("/audio", audioRouter);

app.all("*", (req, res) => res.status(400).json({
    success: false,
    origin: "audio_streaming_service",
    data: {
    message: "This route does not exist",
    result: null} }));

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));