//public class Trash {
//    public BigInteger add(BigInteger smallRight, boolean fromMul)
//    {
//        // TODO : Initialize return value & determine sign
//        BigInteger resultValue;
//        int resultLen;
//
//        // TODO : Assigning variables of result
//        if (fromMul) {
//            resultLen = MAX_INTEGER_SIZE * 2;
//            resultValue = this.getByteArr();
//        } else {
//            resultLen = MAX_INTEGER_SIZE + 1;
//            resultValue = new BigInteger(resultLen);
//        }
//
//        // TODO : Calculate
//        int leftLen = this.getByteArr().length;
//        int rightLen = smallRight.getByteArr().length;
//
//        for (int i = 1; i <= rightLen; i++) {
//            int leftIndex = leftLen - i;
//            int rightIndex = rightLen - i;
//            int returnIndex = resultLen - i;
//
//            byte sum = 0;
//            byte carry = 0;
//            byte tmpResult = 0;
//            byte leftValue = this.getByteArr()[leftIndex];
//            byte rightValue = smallRight.getByteArr()[rightIndex];
//
//            if (leftValue > 10) {
//                carry += (byte)(leftValue / 10);
//                leftValue = (byte)(leftValue % 10);
//            }
//            tmpResult += (byte)(leftValue + rightValue);
//            sum += (byte)(tmpResult % 10);
//            carry += (byte)(tmpResult / 10);
//            resultValue.getByteArr()[returnIndex] = (byte)((leftValue + rightValue) % 10);
//            if (re)
//            resultValue.getByteArr()[i] += (byte)((leftValue + rightValue) / 10);
//        }
//
//        return resultValue;
//    }
//}
