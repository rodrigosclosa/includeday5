package com.ciandt.includeday6.backend.util;

import com.google.api.server.spi.response.ConflictException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by flaviof on 28/10/16.
 */

public class DateUtil {

    public static final String TIMEZONE_BRT = "America/Sao_Paulo";
    public static final String TIMEZONE_UTC = "UTC";
    private static final long MS_EM_UM_SEGUNDO = 1000;
    private static final long MS_EM_UM_MINUTO = MS_EM_UM_SEGUNDO * 60;

    private static final int ULTIMA_HORA = 23;
    private static final int ULTIMO_MINUTO = 59;
    private static final int ULTIMO_SEGUNDO = 59;
    private static final int ULTIMO_MILISSEGUNDO = 999;


    private static final Logger log = Logger.getLogger(DateUtil.class.getName());

    private DateUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * Cria uma data a partir de uma string no formato dd/mm/yyyy
     *
     * @param data Formato {@code dd/MM/yyyy}
     * @return
     */
    public static Date createDate(String data) throws ParseException{
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = sdf.parse(data);
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Erro ao converter data. Por favor use o formato dd/MM/yyyy.");
            throw e;
        }
        return d;
    }

    /**
     * Cria uma data e hora a partir de uma string no formato dd/mm/yyyy hh:mm:ss
     *
     * @param data Formato {@code dd/MM/yyyy HH:mm:ss}
     * @return
     */
    public static Date createDateTime(String data) throws ParseException{
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(data);
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Erro ao converter data. Por favor use o formato dd/MM/yyyy HH:mm:ss.");
            throw e;
        }
        return d;
    }

    /**
     * Cria uma data a partir dos números informados para dia, mês e ano
     *
     * @param dia
     * @param mes
     * @param ano
     * @return {@link Date}
     */
    public static Date createDate(int dia, int mes, int ano) {
        return createDateTime(dia, mes, ano, 0, 0, 0, 0);
    }

    /**
     * Cria uma data a partir dos números informados para dia, mês e ano, hora, minuto, segundo e milisegundo
     *
     * @param dia
     * @param mes
     * @param ano
     * @param hora
     * @param minuto
     * @param segundo
     * @param milisegundos
     * @return {@link Date}
     */
    public static Date createDateTime(int dia, int mes, int ano, int hora, int minuto, int segundo, int milisegundos) {
        Calendar data = new GregorianCalendar();
        data.set(Calendar.YEAR, ano);
        // retira um pois a contagem comeca em 0
        data.set(Calendar.MONTH, mes - 1);
        data.set(Calendar.DAY_OF_MONTH, dia);
        data.set(Calendar.HOUR_OF_DAY, hora);
        data.set(Calendar.MINUTE, minuto);
        data.set(Calendar.SECOND, segundo);
        data.set(Calendar.MILLISECOND, milisegundos);
        return data.getTime();
    }

    /**
     * Cria uma data a partir da data passada mas com hora, minuto, segundo e milisegundos zerados
     *
     * @param date
     * @return {@link Date}
     */
    public static Date getDateFirstHour(Date date) {
        Calendar data = new GregorianCalendar();
        data.setTime(date);
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        return data.getTime();
    }

    /**
     * Cria uma data a partir da data passada mas com horario setado para o ultimo segundo do dia
     *
     * @param date
     * @return {@link Date}
     */
    public static Date getDateLastHour(Date date) {
        Calendar data = new GregorianCalendar();
        data.setTime(date);
        data.set(Calendar.HOUR_OF_DAY, ULTIMA_HORA);
        data.set(Calendar.MINUTE, ULTIMO_MINUTO);
        data.set(Calendar.SECOND, ULTIMO_SEGUNDO);
        data.set(Calendar.MILLISECOND, ULTIMO_MILISSEGUNDO);
        return data.getTime();
    }

    /**
     * Compara duas datas, motivado em razao do Oracle nao prover milisegundos
     *
     * @param data1 ({@link Date}) primeira data a ser comparada
     * @param data2 ({@link Date}) segunda data a ser comparada
     * @return indicador (boolean) de sucesso da comparacao
     * @see {@link #isSameDate(Calendar, Calendar)}
     * @see {@link #isSameTime(Calendar, Calendar)}
     */
    public static boolean equals(Date data1, Date data2) {
        Calendar dataUm = new GregorianCalendar();
        dataUm.setTime(data1);

        Calendar dataDois = new GregorianCalendar();
        dataDois.setTime(data2);

        return (isSameDate(dataUm, dataDois) && isSameTime(dataUm, dataDois));
    }

    /**
     * Compara duas datas, motivado em razao do Oracle nao prover milisegundos
     *
     * @param data1 ({@link Date}) primeira data a ser comparada
     * @param data2 ({@link Date}) segunda data a ser comparada
     * @return indicador (boolean) de sucesso da comparacao
     * @see {@link #isSameDate(Calendar, Calendar)}
     * @see {@link #isSameTime(Calendar, Calendar)}
     */
    public static boolean equals(Date data1, Date data2, boolean comparaSegundos) {
        Calendar dataUm = new GregorianCalendar();
        dataUm.setTime(data1);

        Calendar dataDois = new GregorianCalendar();
        dataDois.setTime(data2);
        if (comparaSegundos) {
            return (isSameDate(dataUm, dataDois) && isSameTime(dataUm, dataDois));
        } else {
            return isSameDate(dataUm, dataDois);
        }

    }

    /**
     * Compara se a data é a mesma nos dois objetos, o horário não é considerado
     *
     * @param dataUm   ({@link Calendar}) primeira data a ser comparada
     * @param dataDois ({@link Calendar}) segunda data a ser comparada
     * @return true se o dia, mês e ano das duas datas são iguais
     */
    private static boolean isSameDate(Calendar dataUm, Calendar dataDois) {
        return (dataUm.get(Calendar.YEAR) == dataDois.get(Calendar.YEAR) && dataUm.get(Calendar.MONTH) == dataDois
                .get(Calendar.MONTH) && dataUm.get(Calendar.DAY_OF_MONTH) == dataDois
                .get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Compara se o horário é o mesmo nos dois objetos, a data não é considerada
     *
     * @param dataUm   ({@link Calendar}) primeira data a ser comparado
     * @param dataDois ({@link Calendar}) segunda data a ser comparado
     * @return true se a hora, minuto e segundo das duas datas são iguais
     */
    private static boolean isSameTime(Calendar dataUm, Calendar dataDois) {
        return (dataUm.get(Calendar.HOUR_OF_DAY) == dataDois.get(Calendar.HOUR_OF_DAY)
                && dataUm.get(Calendar.MINUTE) == dataDois.get(Calendar.MINUTE)
                && dataUm.get(Calendar.SECOND) == dataDois.get(Calendar.SECOND));
    }

    /**
     * Metodo para criar uma data igual a passada mas com referencia diferente
     *
     * @param data ({@link Date}) a ser clonada
     * @return clone da data({@link Date}) enviada
     */
    public static Date clone(Date data) {
        return data == null ? null : (Date) data.clone();
    }

    public static int converteMsEmMinutos(long milisegundos) {
        return (int) (milisegundos / MS_EM_UM_MINUTO);
    }

    public static int converteMsEmSegundos(long milisegundos) {
        return (int) (milisegundos / MS_EM_UM_SEGUNDO);
    }

    /**
     * Compara duas datas
     *
     * @param dateOne ({@link Date}) primeira data a ser comparado
     * @param dateTwo ({@link Date}) segunda data a ser comparado
     * @return 1 maior DateOne > DateTwo, 0 DateOne = DateTwo, -1 DateOne < DateTwo
     */
    public static int compareDate(Date dateOne, Date dateTwo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int iDateOne = Integer.valueOf(sdf.format(dateOne));
        int iDateTwo = Integer.valueOf(sdf.format(dateTwo));

        if (iDateOne > iDateTwo) {
            return 1;
        }
        if (iDateOne < iDateTwo) {
            return -1;
        } else {
            return 0;
        }
    }

    public static Date incrementDate(Date data, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DAY_OF_MONTH, day);

        return cal.getTime();
    }

    public static Date converteBRTtoUTC(Date date) throws ConflictException{
        SimpleDateFormat simpleDateFormatUTC = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        simpleDateFormatUTC.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));

        Date retorno;

        try {
            retorno = createDateTime(simpleDateFormatUTC.format(date));
        } catch (ParseException e) {
            throw new ConflictException("Erro ao parsear data", e);
        }

        return retorno;
    }

    public static Date corrigeTimezone (Date date){
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone(TIMEZONE_BRT));

        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 13);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        log.info(String.format("Hora original: %s. Hora considerada: %s.", date, calendar.getTime()));

        return calendar.getTime();
    }

    public static Date getBRTTimeZoneDate() {
        Calendar calendar = convertCalendar(Calendar.getInstance(), TimeZone.getTimeZone(TIMEZONE_BRT));
        calendar.setTimeZone(TimeZone.getTimeZone(TIMEZONE_BRT));
        return calendar.getTime();
    }

    public static Calendar convertCalendar(final Calendar calendar, final TimeZone timeZone) {
        Calendar ret = new GregorianCalendar(timeZone);
        ret.setTimeInMillis(calendar.getTimeInMillis() +
                timeZone.getOffset(calendar.getTimeInMillis()) -
                TimeZone.getDefault().getOffset(calendar.getTimeInMillis()));
        ret.getTime();
        return ret;
    }
}
