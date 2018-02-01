//
//
//public void paint(Graphics g){
//        BufferedImage myPicture = null;
//        try {
//        myPicture = ImageIO.read(new File("C:/Users/charl/Documents/Project/auroraBorealis/resources/cluedo_board.jpg"));
//        } catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//        }
//
//        int cellWidth = myPicture.getWidth(null);
//
//        int cellHeight = myPicture.getHeight(null);
//
//
//        int dy = (0 % 2) * cellHeight;
//        g.drawImage(myPicture,
//        0, dy,
//        cellWidth, dy+cellHeight,
//        0, 0,
//        cellWidth, cellHeight,
//        null);
//
//
//        g.drawOval(40, 40, 60, 60); //FOR CIRCLE
//        g.drawRect(80, 30, 200, 200); // FOR SQUARE
//        g.drawRect(200, 100, 100, 200); // FOR RECT
//
//}